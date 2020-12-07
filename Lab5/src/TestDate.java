import java.time.LocalDateTime;

public class TestDate implements DateGiver {
	LocalDateTime date = LocalDateTime.of(2020, 12, 07, 00, 00);

	@Override
	public LocalDateTime getDate() {
		return date;
	}

	void setDate(LocalDateTime date) {
		this.date = date;
	}

}