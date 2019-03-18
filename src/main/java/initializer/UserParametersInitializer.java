package initializer;


import content.parameter.ParamType;
import entity.User;
import logic.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.EnumMap;

public class UserParametersInitializer {

    private static final UserParametersInitializer INSTANCE = new UserParametersInitializer();
    private static Logger logger = LogManager.getLogger();

    public static UserParametersInitializer getInstance() {
        return INSTANCE;
    }

    public void setUserParamFromMap(User user, EnumMap<ParamType, String> userParameters) throws ServiceException {
        Integer companyId = Integer.parseInt(userParameters.get(ParamType.LOGIN));
        String hashedPass = BCrypt.hashpw(userParameters.get(ParamType.PASSWORD), BCrypt.gensalt(12));
        user.setPassword(hashedPass);
        user.setFirstName(userParameters.get(ParamType.FIRST_NAME));
        user.setLastName(userParameters.get(ParamType.LAST_NAME));
        user.setEmail(userParameters.get(ParamType.EMAIL));

    }

    public void setUserParamWithNullFromMap(User user, EnumMap<ParamType, String> userParameters) throws ServiceException {
        if (userParameters.get(ParamType.FIRST_NAME) != null) {
            user.setFirstName(userParameters.get(ParamType.FIRST_NAME));
        }
        if (userParameters.get(ParamType.LAST_NAME) != null) {
            user.setLastName(userParameters.get(ParamType.LAST_NAME));
        }
        if (userParameters.get(ParamType.EMAIL) != null) {
            user.setEmail(userParameters.get(ParamType.EMAIL));
        }
        if (!userParameters.get(ParamType.LOGIN).contentEquals("null") && userParameters.get(ParamType.LOGIN) != null) {
            Integer companyId = userParameters.get(ParamType.LOGIN).isEmpty()?0:Integer.parseInt(userParameters.get(ParamType.LOGIN));

        }
    }

    public User copyUserParameter(User userFrom, User userTo) throws ServiceException {
        userTo.setLogin(userFrom.getLogin());
        userTo.setFirstName(userFrom.getFirstName());
        userTo.setLastName(userFrom.getLastName());
        userTo.setEmail(userFrom.getEmail());
        userTo.setIsAdmin(userFrom.getIsAdmin());
        return userTo;
    }

}
