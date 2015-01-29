import ua.artcode.dynamic_compile.MethodInvoker;

public class _fact31678 implements MethodInvoker {

    public _fact31678() {
    }

    @Override
    public Object call(Object...args) {
                Integer arg0 = (Integer) args[0];
                return fact(arg0);
    }

    public Integer fact(Integer num) {
  return num < 2 ? 1 : num * fact(num-1);

}

}
