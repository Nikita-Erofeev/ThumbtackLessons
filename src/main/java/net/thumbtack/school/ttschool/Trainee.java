package net.thumbtack.school.ttschool;

import java.io.Serializable;

public class Trainee implements Serializable {
    private String firstName;
    private String lastName;
    private int rating;

    private boolean isNotEmpty(String string) {
        return string != null && !string.equals("");
    }

    public Trainee(String firstName, String lastName, int rating) throws TrainingException {
        if (isNotEmpty(firstName)) {
            this.firstName = firstName;
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }

        if (isNotEmpty(lastName)) {
            this.lastName = lastName;
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        }

        if (rating > 0 && rating < 6) {
            this.rating = rating;
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws TrainingException {
        if (isNotEmpty(firstName)) {
            this.firstName = firstName;
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws TrainingException {
        if (isNotEmpty(lastName)) {
            this.lastName = lastName;
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws TrainingException {
        if (rating > 0 && rating < 6) {
            this.rating = rating;
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainee trainee = (Trainee) o;

        if (rating != trainee.rating) return false;
        if (firstName != null ? !firstName.equals(trainee.firstName) : trainee.firstName != null) return false;
        return lastName != null ? lastName.equals(trainee.lastName) : trainee.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }
}
