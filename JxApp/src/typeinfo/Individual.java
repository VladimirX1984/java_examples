/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeinfo;

/**
 *
 * @author Vladimir
 */
public class Individual implements Comparable<Individual> {

    private static long counter = 0;
    private final long mId = counter++;
    private String mName;

    public Individual(String name) {
        this.mName = name;
    }
    // 'name' is optional:

    public Individual() {
    }

    public String toString() {
        return getClass().getSimpleName()
                + (mName == null ? "" : " " + mName);
    }

    public long getId() {
        return mId;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Individual
                && mId == ((Individual) o).mId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (mName != null) {
            result = 37 * result + mName.hashCode();
        }
        result = 37 * result + (int) mId;
        return result;
    }

    @Override
    public int compareTo(Individual arg) {
        // Compare by class name first:
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();
        int firstCompare = first.compareTo(argFirst);
        if (firstCompare != 0) {
            return firstCompare;
        }
        if (mName != null && arg.mName != null) {
            int secondCompare = mName.compareTo(arg.mName);
            if (secondCompare != 0) {
                return secondCompare;
            }
        }
        return (arg.mId < mId ? -1 : (arg.mId == mId ? 0 : 1));
    }
}
