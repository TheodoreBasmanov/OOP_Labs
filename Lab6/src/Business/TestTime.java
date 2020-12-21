package Business;

import java.time.LocalDateTime;

public class TestTime implements TimeGiver {
	LocalDateTime date = LocalDateTime.of(2020, 12, 21, 00, 00);

	@Override
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
