package reader;



import content.RequestContent;
import content.parameter.ParamType;

import java.util.EnumMap;
import java.util.EnumSet;

public class InputReader {

    private static final InputReader INSTANCE = new InputReader();

    public static InputReader getInstance() {
        return INSTANCE;
    }

    public EnumMap<ParamType, String> readParameters(RequestContent content, EnumSet<ParamType> parametersToValidate) {
        EnumMap<ParamType, String> parameters = new EnumMap<>(ParamType.class);
        parametersToValidate.stream().forEach(e -> parameters.put(e, content.getParameter(e.toString().toLowerCase())));
        return parameters;
    }

    public EnumMap<ParamType, String> readAttributes(RequestContent content, EnumSet<ParamType> attributesToValidate) {
        EnumMap<ParamType, String> attributes = new EnumMap<>(ParamType.class);
        attributesToValidate.stream().forEach(e
                -> attributes.put(e, (String) content.getSessionAttribute(e.toString().toLowerCase())));
        return attributes;
    }
}
