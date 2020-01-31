package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private List<Trainee> trainees = new LinkedList<>();

    public Group(String name, String room) throws TrainingException {
        if (name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        } else {
            this.name = name;
        }
        if (room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        } else {
            this.room = room;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        } else {
            this.name = name;
        }
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        } else {
            this.room = room;
        }
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.remove(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTrainee(int index) throws TrainingException {
        if (trainees.size() <= index || index < 0) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        } else {
            trainees.remove(index);
        }
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee tr : trainees) {
            if (tr.getFirstName().equals(firstName)) {
                return tr;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee tr : trainees) {
            if (tr.getFullName().equals(fullName)) {
                return tr;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        trainees.sort(Comparator.comparing(Trainee::getRating, (s1, s2) -> {
            return s2.compareTo(s1);
        }));
    }

    public void reverseTraineeList() {
        int count = trainees.size() / 2;
        int last = trainees.size() - 1;
        Trainee box;
        for (int i = 0; i < count; i++) {
            box = trainees.get(i);
            trainees.set(i, trainees.get(last));
            trainees.set(last, box);
            last--;
        }
    }

    public void rotateTraineeList(int positions) {
        LinkedList linkedTrainee = (LinkedList) trainees;
        if (positions > 0) {
            for (int i = 0; i < positions; i++) {
                linkedTrainee.addFirst(linkedTrainee.pollLast());
            }
        } else {
            for (int i = 0; i < Math.abs(positions); i++) {
                linkedTrainee.addLast(linkedTrainee.pollFirst());
            }
        }
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        if (trainees.size() == 0) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        LinkedList<Trainee> result = new LinkedList<>();
        int max = 1;
        for (Trainee trainee : trainees) {
            if (trainee.getRating() > max) {
                result.clear();
                result.add(trainee);
                max = trainee.getRating();
            } else if (trainee.getRating() == max) {
                result.add(trainee);
            }
        }
        return result;
    }

    public boolean hasDuplicates() {
        LinkedList<Trainee> forResult = new LinkedList<>();
        for (Trainee trainee : trainees) {
            if (forResult.contains(trainee)) {
                return true;
            }else {
                forResult.add(trainee);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (room != null ? !room.equals(group.room) : group.room != null) return false;
        return trainees != null ? trainees.equals(group.trainees) : group.trainees == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (trainees != null ? trainees.hashCode() : 0);
        return result;
    }
}
