public class Insertion {
    private Object[] getMethodParameters(Method method, HttpServletRequest request, ValidationsError validationsError)
            throws Exception {
        Parameter[] parameters = method.getParameters();
        Object[] parameterValues = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (!parameters[i].isAnnotationPresent(Param.class)
                    && !parameters[i].isAnnotationPresent(ParamObject.class)
                    && !parameters[i].getType().equals(CustomSession.class)) {
                throw new Exception("ETU002380: les attributs doivent etre annoter par Param ou ParamObject");
            }
            if (parameters[i].getType().equals(CustomSession.class)) {
                CustomSession session = new CustomSession(request.getSession());
                parameterValues[i] = session;
            }
            if (parameters[i].isAnnotationPresent(Param.class)) {
                Param param = parameters[i].getAnnotation(Param.class);
                if (parameters[i].getType() == Part.class) {
                    Part file = request.getPart(param.value());
                    upload(file);
                    parameterValues[i] = file;
                } else {
                    String paramValue = request.getParameter(param.value());
                    parameterValues[i] = convertParameter(paramValue, parameters[i].getType()); // Assuming all
                                                                                                // parameters
                }
                // are strings for
                // simplicity
            }
            // Vérifie si le paramètre est annoté avec @RequestObject
            else if (parameters[i].isAnnotationPresent(ParamObject.class)) {
                Class<?> parameterType = parameters[i].getType(); // Récupère le type du paramètre (le type de l'objet à
                                                                  // créer)
                Object parameterObject = parameterType.getDeclaredConstructor().newInstance(); // Crée une nouvelle
                                                                                               // instance de cet objet

                // Parcourt tous les champs (fields) de l'objet
                for (Field field : parameterType.getDeclaredFields()) {
                    RequestParam param = field.getAnnotation(RequestParam.class);
                    String fieldName = field.getName(); // Récupère le nom du champ
                    // parameterType.getSimpleName().toLowerCase() + "." +
                    String paramName = (param != null) ? param.value() : fieldName; // Forme le nom du
                                                                                    // paramètre de la
                                                                                    // requête attendu
                    String paramValue = request.getParameter(paramName); // Récupère la valeur du paramètre de la
                                                                         // requête
                    // Vérifie si la valeur du paramètre n'est pas null (si elle est trouvée dans la
                    // requête)
                    if (paramValue != null) {
                        validateFieldValue(paramValue, field, validationsError);
                        Object convertedValue = convertParameter(paramValue, field.getType()); // Convertit la valeur de
                                                                                               // la requête en type de
                                                                                               // champ requis

                        // Construit le nom du setter
                        String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                        Method setter = parameterType.getMethod(setterName, field.getType()); // Récupère la méthode
                                                                                              // setter correspondante
                        setter.invoke(parameterObject, convertedValue); // Appelle le setter pour définir la valeur
                                                                        // convertie dans le champ de l'objet
                    }
                }
                parameterValues[i] = parameterObject; // Stocke l'objet créé dans le tableau des arguments
            } else {

            }
        }

        return parameterValues;
    }
}
