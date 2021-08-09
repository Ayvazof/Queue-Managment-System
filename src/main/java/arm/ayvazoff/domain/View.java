package arm.ayvazoff.domain;

public final class View {
    public interface Id {}
    public interface IdName extends Id {}
    public interface IdNameSurname extends IdName {}
    public interface IdNameSurnameLogin extends IdNameSurname {}
    public interface IdNameSurnameLoginPassword extends IdNameSurnameLogin {}
    public interface FullMessage extends IdNameSurnameLogin {}
}
