package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_MATRIC = new Prefix("i/");
    public static final Prefix PREFIX_COURSECODE = new Prefix("c/");

    public static final Prefix PREFIX_GRADEBOOK_MODULE = new Prefix("c/");
    public static final Prefix PREFIX_GRADEBOOK_ITEM = new Prefix("i/");

    public static final Prefix PREFIX_COURSENAME = new Prefix("n/");
    public static final Prefix PREFIX_FACULTY = new Prefix("f/");
    public static final Prefix PREFIX_MODULE_NAME = new Prefix("n/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("c/");
    public static final Prefix PREFIX_CLASSNAME = new Prefix("c/");
    public static final Prefix PREFIX_MODULECODE = new Prefix("m/");
    public static final Prefix PREFIX_MAXENROLLMENT = new Prefix("e/");

    public static final Prefix PREFIX_NOTE_MODULE_CODE = new Prefix("m/");
    public static final Prefix PREFIX_NOTE_DATE = new Prefix("d/");

}
