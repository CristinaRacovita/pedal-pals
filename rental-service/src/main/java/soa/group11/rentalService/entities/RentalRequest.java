package soa.group11.rentalService.entities;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "requests")
public class RentalRequest {
    @Id
    private String id;
    private int bikeOwnerId;
    private int bikeRequesterId;
    private String bikeId;
    private String status;
    private Date startDate;
    private Date endDate;

    public RentalRequest(int bikeOwnerId, int bikeRequesterId, String bikeId, String status, String startDate,
            String endDate) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
        this.startDate = formatDate(startDate);
        this.endDate = formatDate(endDate);
    }

    public RentalRequest(String id, int bikeOwnerId, int bikeRequesterId, String bikeId, String status,
            String startDate, String endDate) {
        this.id = id;
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
        this.startDate = formatDate(startDate);
        this.endDate = formatDate(endDate);
    }

    private Date formatDate(String date) {
        if (date.length() == 10) {
            date += " 02:00";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date formattedDate;

        try {
            formattedDate = formatter.parse(date);
        } catch (ParseException e) {
            formattedDate = null;
        }

        return formattedDate;
    }

    public String getStringStartDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.startDate);
    }

    public String getStringEndDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.endDate);
    }
}
