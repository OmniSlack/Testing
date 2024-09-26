package com.homework.exercise.lecture13.exercise1;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Person {
    private final String name;
    private final String sex;
    private final String religion;
    private final String languageSpoken;
    private final String nationality;
    private final String EGN;
    private final LocalDate dateOfBirth;
    private final int age;
    private String job;
    private String countryOfResidence;

    public Person(String name, String sex, String religion, String languageSpoken, String job, String nationality, String EGN, String countryOfResidence) {
        if (!validateEGN(EGN)) {
            throw new IllegalArgumentException("Invalid EGN");
        }
        if (!validateSex(sex)) {
            System.out.println("Invalid sex provided. Continuing execution.");
        }
        this.name = name;
        this.sex = sex;
        this.religion = religion;
        this.languageSpoken = languageSpoken;
        this.job = job;
        this.nationality = nationality;
        this.EGN = EGN;
        this.dateOfBirth = extractDateOfBirth(EGN);
        this.age = calculateAge(this.dateOfBirth);
        this.countryOfResidence = countryOfResidence;
    }

    private boolean validateEGN(String EGN) {
        return EGN != null && EGN.length() == 10 && EGN.matches("\\d+");
    }

    private boolean validateSex(String sex) {
        return sex.equalsIgnoreCase("male") || sex.equalsIgnoreCase("female");
    }

    private LocalDate extractDateOfBirth(String EGN) {
        String dob = EGN.substring(0, 6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        if (birthDate.getYear() > LocalDate.now().getYear()) {
            birthDate = birthDate.minusYears(100);
        }
        return birthDate;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public abstract void sayHello();

    public void celebrateEaster() {
        if (religion.equalsIgnoreCase("orthodox") || religion.equalsIgnoreCase("catholic")) {
            System.out.println("I celebrate Easter.");
        } else {
            System.out.println("I do not celebrate Easter.");
        }
    }

    public boolean isAdult() {
        switch (countryOfResidence) {
            case "Bulgaria":
            case "Italy":
                return age >= 18;
            case "USA":
                return age >= 21;
            default:
                throw new IllegalArgumentException("Unknown country: " + countryOfResidence);
        }
    }

    public boolean canTakeLoan() {
        return job != null && !job.isEmpty();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
}

