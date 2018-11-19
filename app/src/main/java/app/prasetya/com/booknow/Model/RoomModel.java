package app.prasetya.com.booknow.Model;

public class RoomModel {
    private int ImageRoom;
    private String TittleRoom;
    private String FacilityRoom;
    private String PriceRoom;

    public RoomModel(int imageRoom, String tittleRoom, String priceRoom) {
        ImageRoom = imageRoom;
        TittleRoom = tittleRoom;
        PriceRoom = priceRoom;
    }

    public int getImageRoom() {
        return ImageRoom;
    }

    public void setImageRoom(int imageRoom) {
        ImageRoom = imageRoom;
    }

    public String getTittleRoom() {
        return TittleRoom;
    }

    public void setTittleRoom(String tittleRoom) {
        TittleRoom = tittleRoom;
    }

    public String getFacilityRoom() {
        return FacilityRoom;
    }

    public void setFacilityRoom(String facilityRoom) {
        FacilityRoom = facilityRoom;
    }

    public String getPriceRoom() {
        return PriceRoom;
    }

    public void setPriceRoom(String priceRoom) {
        PriceRoom = priceRoom;
    }

}
