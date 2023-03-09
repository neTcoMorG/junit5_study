public class Study {

    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalStateException("0보다 커야합니다");
        }
        this.limit = limit;
    }

    public int getLimit () {
        return this.limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }
}
