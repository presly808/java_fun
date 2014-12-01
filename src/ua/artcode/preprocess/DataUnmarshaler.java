package ua.artcode.preprocess;

import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestCase;


public class DataUnmarshaler {

    public void convert(TestCase testCase) {
        for (DataPoint step : testCase.getDataPointList()) {
            for (TestArg arg : step.getIn()) {
                arg.setValue(convertDispatcher(arg));
            }

            //TODO Test this section
            TestArg expected = step.getExpected();
            expected.setValue(convertDispatcher(expected));

        }
    }

    //TODO think about collection (List, Set, Map)
    private Object convertDispatcher(TestArg arg) {
        String type = arg.getType();
        String value = arg.getValue().toString();
        if ("byte".equals(type) || "java.lang.Byte".equals(type)) {
            return unmarshalByte(value);
        } else if ("short".equals(type) || "java.lang.Short".contains(type)) {
            return unmarshalShort(value);
        } else if ("int".equals(type) || "java.lang.Integer".contains(type)) {
            return unmarshalInteger(value);
        } else if ("long".equals(type) || "java.lang.Long".contains(type)) {
            return unmarshalLong(value);
        } else if ("float".equals(type) || "java.lang.Float".contains(type)) {
            return unmarshalFloat(value);
        } else if ("double".equals(type) || "java.lang.Double".contains(type)) {
            return unmarshalDouble(value);
        } else if ("boolean".equals(type) || "java.lang.Boolean".contains(type)) {
            return unmarshalBoolean(value);
        } else if ("char".equals(type) || "java.lang.Character".contains(type)) {
            return unmarshalCharacter(value);
        } else if ("String".equals(type) || "java.lang.String".contains(type)) {
            return arg.getValue();
        } else {
            return arg.getValue(); // TODO refactor this place
        }
    }

    private Byte unmarshalByte(String s) {
        return Byte.parseByte(s);
    }

    private Short unmarshalShort(String s) {
        return Short.parseShort(s);
    }

    private Integer unmarshalInteger(String s) {
        return Integer.parseInt(s);
    }

    private Long unmarshalLong(String s) {
        return Long.parseLong(s);
    }

    private Float unmarshalFloat(String s) {
        return Float.parseFloat(s);
    }

    private Double unmarshalDouble(String s) {
        return Double.parseDouble(s);
    }

    private Boolean unmarshalBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    private Character unmarshalCharacter(String s) {
        return s.charAt(0);
    }

}
