/**
 * Defines class for a virus in the simulator
 * @author Ethan Hu
 * @author Aryav Taneja
 */

package src;

public class Virus {

    /**
     * @param infectability    The probability that a person who interacts with an
     *                         infected person also gets infected. 0-1 double.
     * @param mortality        A modifier for how lethal the disease is, although
     *                         final chance of death is also a factor of susceptible
     *                         age group and preexisting conditions. 0-1 double.
     * @param susceptibilities A boolean array of which age groups (children,
     *                         adults, seniors) are more susceptible to the disease
     *                         than others. Multiple are possible (i.e. children and
     *                         seniors)
     * @param incubation       The number of days which the virus incubates in an
     *                         infected person. The person is able to spread the
     *                         disease in this time, but isn't able to die during
     *                         this time.
     * @param resistance       The number of days that the person "feels symptoms"
     *                         and is able to die. Every day of this period, they
     *                         have a chance of death based on mortality, age and
     *                         preexisting conditions. If they survive these many
     *                         days, they are considered recovered and cannot be 
     *                         infected again.
     */
	private String name;
    private double infectability;
	private double mortality;
    private boolean[] susceptibilities;
    private int incubation;
    private int resistance;
    
    public Virus() {
    	
    }
    public Virus(double infectability, double mortality, boolean[] susceptibilities, int incubation, int resistance) {
        this.infectability = infectability;
        this.mortality = mortality;
        this.susceptibilities = susceptibilities;
        this.incubation = incubation;
        this.resistance = resistance;
    }

    public double getInfectability() {
        return infectability;
    }

    public double getMortality() {
        return mortality;
    }

    public boolean[] getSusceptibilities() {
        return susceptibilities;
    }

    public int getIncubation() {
        return incubation;
    }

    public int getResistance() {
        return resistance;
    }
    public String getName() {
    	return name;
    }
    public void setInfectability(double infectability) {
		this.infectability = infectability;
	}

	public void setMortality(double mortality) {
		this.mortality = mortality;
	}

	public void setSusceptibilities(boolean[] susceptibilities) {
		this.susceptibilities = susceptibilities;
	}

	public void setIncubation(int incubation) {
		this.incubation = incubation;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

}