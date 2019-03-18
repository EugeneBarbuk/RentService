package content.parameter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static content.constant.ContentConstant.*;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;

public enum ParamType implements Predicate<String> {

    LOGIN(Pattern.compile(LOGIN_REGEX, UNICODE_CHARACTER_CLASS).asPredicate()),
    PASSWORD(t -> (t.length() >= PASSWORD_LENGTH_MIN) && (t.length() <= PASSWORD_LENGTH_MAX)),
    EMAIL(Pattern.compile(EMAIL_REGEX).asPredicate()),
    FIRST_NAME(t -> (t.length() <= FIRST_NAME_LENGTH_MAX) && (!t.isEmpty())),
    LAST_NAME(t -> (t.length() <= LAST_NAME_LENGTH_MAX) && (!t.isEmpty())),
    WEB(t -> (t.length() <= WEB_LENGTH_MAX)),
    PRICE(t -> (Integer.parseInt(t) >= PRICE_MIN) && Integer.parseInt(t) <= PRICE_MAX),
    MEMBER_TO_ADD_LOGIN,
    MEMBER_TO_DELETE_LOGIN;
    

    private Predicate<String> predicate;
    private static Logger logger = LogManager.getLogger();

    ParamType() {
    }

    ParamType(Predicate<String> predicate) {
        this.predicate = predicate;
    }
    
    public String getName() {
        return this.toString().toLowerCase();
    }

    public Predicate getPredicate() {
        return predicate;
    }

    @Override
    public boolean test(String t) {
        return predicate.test(t);
    }
}
