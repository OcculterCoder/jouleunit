package org.qualitune.jouleunit.coordinator;

/**
 * Simple representation of a test case that have been executed during JouleUnit
 * profiling.
 * 
 * Contains time stamps for start and end of the test case.
 * 
 * @author Claas Wilke
 */
public class TestCaseProfile implements Comparable<TestCaseProfile> {

	/**
	 * The cached consumed energy of this {@link TestCaseProfile} if it has been
	 * computed before.
	 */
	private double cachedConsumedEnergy;

	/**
	 * The cached duration of this {@link TestCaseProfile} if it has been
	 * computed before.
	 */
	private long cachedDuration;

	/**
	 * The cached power rate of this {@link TestCaseProfile} if it has been
	 * computed before.
	 */
	private double cachedPowerRate;

	/** Time stamp for finishing the test case. */
	private long endTime;

	/** If <code>true</code> the test case failed. */
	private boolean failed = false;

	/**
	 * The id of the test case (typically a combination of its method and class
	 * name).
	 */
	private String id;

	/** Time stamp for starting the test case. */
	private long startTime;

	/**
	 * The tag of the test case (a further separator to differentiate the same
	 * tested functionality under different conditions (e.g., sending a mail
	 * with and without attachment).
	 */
	private String tag;

	/**
	 * The {@link TestSuiteProfile} this {@link TestCaseProfile} belongs to.
	 * <strong>Can be <code>null</code>!</strong>.
	 */
	private TestSuiteProfile testSuiteProfile;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(TestCaseProfile arg0) {
		return new Long(this.getStartTime()).compareTo(new Long(arg0
				.getStartTime()));
	}

	/**
	 * Returns the consumed energy of this {@link TestCaseProfile}. <strong>Can
	 * only be computed if the {@link TestSuiteProfile} has been set before.
	 * Otherwise <code>0</code> will be returned.</strong>
	 */
	public double getConsumedEnergy() {

		if (cachedConsumedEnergy == 0 && null != testSuiteProfile)
			cachedConsumedEnergy = testSuiteProfile.getConsumedEnergy(this);
		// no else.

		return cachedConsumedEnergy;
	}

	/** @return Time stamp for finishing the test case. */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @return The id of the test case (typically a combination of its method
	 *         and class name).
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the average power rate of this {@link TestCaseProfile}.
	 * <strong>Can only be computed if the {@link TestSuiteProfile} has been set
	 * before. Otherwise <code>0</code> will be returned.</strong>
	 */
	public double getPowerRate() {

		if (cachedPowerRate == 0 && null != testSuiteProfile
				&& getDuration() != 0)
			cachedPowerRate = getConsumedEnergy() * 1000 / getDuration();
		// no else.

		return cachedPowerRate;
	}

	/**
	 * Returns the duration of this {@link TestCaseProfile}. <strong>Can only be
	 * computed if the {@link TestSuiteProfile} has been set before. Otherwise
	 * <code>0</code> will be returned.</strong>
	 */
	public long getDuration() {

		if (cachedDuration == 0 && null != testSuiteProfile)
			cachedDuration = testSuiteProfile.getDuration(this);
		// no else.

		return cachedDuration;
	}

	/** @return Time stamp for starting the test case. */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @return The tag of the test case (a further separator to differentiate the
	 *         same tested functionality under different conditions (e.g.,
	 *         sending a mail with and without attachment).
	 */
	public String getTag() {
		return tag;
	}

	/** @return If <code>true</code> the test case failed. */
	public boolean isFailed() {
		return failed;
	}

	/**
	 * @param endTime
	 *            Time stamp for finishing the test case.
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets whether or not the representive test case failed.
	 * 
	 * @param failed
	 *            If <code>true</code> the test case failed (default is
	 *            <code>false</code>).
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	/**
	 * @param id
	 *            The id of the test case (typically a combination of its method
	 *            and class name).
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param startTime
	 *            Time stamp for starting the test case.
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @param tag
	 *            The tag of the test case (a further separator to differentiate
	 *            the same tested functionality under different conditions
	 *            (e.g., sending a mail with and without attachment).
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Sets the {@link TestSuiteProfile} this {@link TestCaseProfile} belongs to
	 * 
	 * @param testSuiteProfile
	 *            The {@link TestSuiteProfile} this {@link TestCaseProfile}
	 *            belongs to.
	 */
	public void setTestSuiteProfile(TestSuiteProfile testSuiteProfile) {
		this.testSuiteProfile = testSuiteProfile;
	}
}
