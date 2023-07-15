package com.lanou.entity;

public class StudentDO {
    private Integer id;
    private String name;
    private String no;
    private String homeTown;
    private Double cnScore;
    private Double enScore;
    private Double mathScore;

    @Override
    public String toString() {
        return "StudentDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", cnScore=" + cnScore +
                ", enScore=" + enScore +
                ", mathScore=" + mathScore +
                '}';
    }

    public StudentDO(Integer id, String name, String no, String homeTown, Double cnScore, Double enScore, Double mathScore) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.homeTown = homeTown;
        this.cnScore = cnScore;
        this.enScore = enScore;
        this.mathScore = mathScore;
    }

    public StudentDO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Double getCnScore() {
        return cnScore;
    }

    public void setCnScore(Double cnScore) {
        this.cnScore = cnScore;
    }

    public Double getEnScore() {
        return enScore;
    }

    public void setEnScore(Double enScore) {
        this.enScore = enScore;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

	public int add(StudentDO studentDO) {
		return 0;
	}
}
