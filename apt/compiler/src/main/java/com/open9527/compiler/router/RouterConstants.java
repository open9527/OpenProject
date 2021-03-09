package com.open9527.compiler.router;


/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class RouterConstants {

    private static final String SEPARATOR = "_";
    static final String PROJECT = "Router";
    static final String NAME_OF_GROUP = PROJECT + SEPARATOR + "Group" + SEPARATOR;
    static final String NAME_OF_ROOT = PROJECT + SEPARATOR + "Root" + SEPARATOR;

    static final String PATH = "com.open9527.router";
    static final String I_SERVICE = PATH + ".inter.IService";
    static final String I_ROUTE_GROUP = PATH + ".inter.IRouteGroup";
    static final String I_ROUTE_ROOT = PATH + ".inter.IRouteRoot";
    static final String I_EXTRA = PATH + ".inter.IExtra";

    static final String ANN_TYPE_EXTRA = "com.open9527.annotation.router.Extra";
    static final String PACKAGE_OF_GENERATE_FILE = "com.open9527.router.routes";

    static final String ARGUMENTS_NAME = "moduleName";
    static final String ACTIVITY = "android.app.Activity";

    static final String METHOD_LOAD_INTO = "loadInto";
    static final String METHOD_LOAD_EXTRA = "loadExtra";

    static final String PARCELABLE = "android.os.Parcelable";

    private static final String LANG = "java.lang";
    public static final String BYTE = LANG + ".Byte";
    public static final String SHORT = LANG + ".Short";
    static final String INTEGER = LANG + ".Integer";
    public static final String LONG = LANG + ".Long";
    public static final String FLOAT = LANG + ".Float";
    public static final String DOUBEL = LANG + ".Double";
    public static final String BOOLEAN = LANG + ".Boolean";
    static final String STRING = LANG + ".String";
    public static final String ARRAY = "ARRAY";

    static final String ARRAYLIST = "java.util.ArrayList";
    static final String LIST = "java.util.List";

    static final String BYTEARRAY = "byte[]";
    static final String SHORTARRAY = "short[]";
    static final String BOOLEANARRAY = "boolean[]";
    static final String CHARARRAY = "char[]";
    static final String DOUBLEARRAY = "double[]";
    static final String FLOATARRAY = "float[]";
    static final String INTARRAY = "int[]";
    static final String LONGARRAY = "long[]";
    static final String STRINGARRAY = "java.lang.String[]";


    static final String NAME_OF_EXTRA = SEPARATOR + "Extra";

}
