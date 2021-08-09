package arm.ayvazoff.domain;

public enum State {
    WAIT("wait"), PASSED("passed"), DONE("done"), PROCESS("process");

    private  String state;

    State(String s) {
        state = s;
    }
}
