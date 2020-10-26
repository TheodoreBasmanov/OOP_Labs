public class Centaur extends LandTransport{
	private Centaur(double speed, double restInterval, double restDuration) {
		super(speed, restInterval, restDuration);
	}

	Centaur() {
		this(15, 8, 2);
	}
}
