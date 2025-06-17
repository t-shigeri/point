package bean;

public class Student {
    private String studentId;
    private String name;
    private int enrollmentYear;
    private String classId;
    private boolean isEnrolled;
    private String className; // クラス名を表示用に追加

    // --- コンストラクタ ---
    public Student() {}

    public Student(String studentId, String name, int enrollmentYear, String classId, boolean isEnrolled, String className) {
        this.studentId = studentId;
        this.name = name;
        this.enrollmentYear = enrollmentYear;
        this.classId = classId;
        this.isEnrolled = isEnrolled;
        this.className = className;
    }

    // --- getter / setter ---
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEnrollmentYear() { return enrollmentYear; }
    public void setEnrollmentYear(int enrollmentYear) { this.enrollmentYear = enrollmentYear; }

    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }

    public boolean isEnrolled() { return isEnrolled; }
    public void setEnrolled(boolean enrolled) { isEnrolled = enrolled; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

	public void setSchoolCd(String string) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public String getSchoolCd() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	private Integer point;  // または int point;

	public Integer getPoint() {
	    return point;
	}

	public void setPoint(Integer point) {
	    this.point = point;
	}
}
