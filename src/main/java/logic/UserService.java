package logic;


import content.CommandResult;
import content.RequestContent;
import content.attribute.AttributeType;
import content.parameter.ParamType;
import dao.DaoException;
import dao.UserDaoImpl;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import reader.InputReader;
import util.ConfigurationManager;
import util.MessageManager;
import validator.ParameterValidator;

import java.util.EnumMap;
import java.util.EnumSet;

import static content.attribute.AttributeType.LANGUAGE;
import static content.attribute.AttributeType.USER;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private static Logger logger = LogManager.getLogger();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public CommandResult showProfile(RequestContent content, EnumSet<ParamType> parametersToValidate) throws ServiceException {
        return new CommandResult(ConfigurationManager.getProperty("path.page.profile"), CommandResult.ResponseType.FORWARD);
    }

    public CommandResult login(RequestContent content,
            EnumSet<ParamType> parametersToValidate) throws ServiceException {
        User user;
        CommandResult router = new CommandResult(ConfigurationManager.
                getProperty("path.page.login"), CommandResult.ResponseType.FORWARD);
        String lang = content.getSessionAttribute(LANGUAGE.getAttribute()).toString().toUpperCase();
        StringBuilder wrongParameters = new StringBuilder();
        EnumMap<ParamType, String> userParameters = InputReader.getInstance().
                readParameters(content, parametersToValidate);
        boolean inputIsValid = ParameterValidator.getInstance().
                validate(userParameters, wrongParameters);
        if (inputIsValid) {
            user = new User();
            boolean checkLog = checkLogin(user);
            if (checkLog) {
                content.setSessionAttribute(USER.getAttribute(), user);
                router = new CommandResult(ConfigurationManager.getProperty("path.page.main"),
                        CommandResult.ResponseType.REDIRECT);
            }
        } else {
            content.setAttribute(AttributeType.FAILURE_MESSAGE.getAttribute(), MessageManager.
                    valueOf(lang).getMessage("message.loginerror"));
        }
        return router;
    }

    public CommandResult register(RequestContent content,
            EnumSet<ParamType> parametersToValidate) throws ServiceException {
        User user;
        CommandResult router = new CommandResult(ConfigurationManager.
                getProperty("path.page.registration"), CommandResult.ResponseType.FORWARD);
        String lang = content.getSessionAttribute(LANGUAGE.getAttribute()).toString().toUpperCase();
        StringBuilder wrongParameters = new StringBuilder();
        EnumMap<ParamType, String> userParameters = InputReader.getInstance().
                readParameters(content, parametersToValidate);
        boolean inputIsValid = ParameterValidator.getInstance().
                validate(userParameters, wrongParameters);
        if (!inputIsValid) {
            content.setAttribute(AttributeType.FAILURE_MESSAGE.getAttribute(),
                    MessageManager.valueOf(lang).getMessage("message.invalid.parameter")
                    + wrongParameters.toString());
            return router;
        }
        user = findUserById(userParameters.get(ParamType.LOGIN));
        if (user == null) {
            create(userParameters);
            router = new CommandResult(ConfigurationManager.getProperty(
                    "path.page.successRegistration"), CommandResult.ResponseType.REDIRECT);
        } else {
            content.setAttribute(AttributeType.FAILURE_MESSAGE.getAttribute(),
                    MessageManager.valueOf(lang).getMessage("message.loginexists"));
        }
        return router;
    }



    public boolean checkLogin(User userToCheck) throws ServiceException {
        User userSaved;
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            userSaved = userDao.findEntityById(userToCheck.getLogin());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        if (userSaved != null) {
            boolean check = BCrypt.checkpw(userToCheck.getPassword(), userSaved.getPassword());
            if (check) {
                UserParametersInitializer.getInstance().copyUserParameter(userSaved, userToCheck);
                return check;
            }
        }
        return false;
    }

    public void create(EnumMap<ParamType, String> userParameters) throws ServiceException {
        String hashedPass = BCrypt.hashpw(userParameters.get(ParamType.PASSWORD), BCrypt.gensalt(12));
        User user = new User(userParameters.get(ParamType.LOGIN), hashedPass);
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(User user, EnumMap<ParamType, String> userParameters) throws ServiceException {
        UserParametersInitializer.getInstance().setUserParamFromMap(user, userParameters);
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            userDao.update(user);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public User findUserById(String login) throws ServiceException {
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            return userDao.findEntityById(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Set<User> findUsersNotInTenderCommitteeByCompanyId(Integer tenderId,
            Integer companyId) throws ServiceException {
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            return userDao.findUsersNotInTenderCommitteeByCompanyId(tenderId, companyId);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }
}
