package validator;



import content.parameter.ParamType;

import java.util.EnumSet;

public class ParamTypeCollection {

    public static final EnumSet<ParamType> userParameters = EnumSet.range(ParamType.PASSWORD, ParamType.LOGIN);

    public static final EnumSet<ParamType> loginParameters = EnumSet.range(ParamType.LOGIN, ParamType.PASSWORD);

}
