import ua.artcode.dynamic_compile.MethodInvoker;

public class _divide08999 implements MethodInvoker {

    public _divide08999() {
    }

    @Override
    public Object call(Object...args) {
                Integer arg0 = (Integer) args[0];
                Integer arg1 = (Integer) args[1];
                return divide(arg0,arg1);
    }

    public Integer divide(Integer a1,Integer a2) {

  return a1 / a2;
}

}
