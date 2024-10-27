package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *  @author javiersolanop
 */
@Embeddable
public class SeatPK implements iJSON {

    @Column(name = "cinema_room")
    private Integer atrCinemaRoom;

    @Column(name = "row")
    private Character atrRow;

    @Column(name = "num_column")
    private Integer atrNumColumn;

    public SeatPK(){}

    public SeatPK(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn)
    {
        atrCinemaRoom = prmCinemaRoom;
        atrRow = prmRow;
        atrNumColumn = prmNumColumn;
    }

    public Integer getCinemaRoom()
    {
        return atrCinemaRoom;
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrCinemaRoom = prmCinemaRoom;
    }

    public Character getRow()
    {
        return atrRow;
    }

    public void setRow(Character prmRow)
    {
        atrRow = prmRow;
    }

    public Integer getNumColumn()
    {
        return atrNumColumn;
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrNumColumn = prmNumColumn;
    }

    @Override
    public JSON toJSON() throws Exception {
        JSON objJson = new JSON();
        objJson.add("cinemaRoom", atrCinemaRoom);
        objJson.add("row", atrRow);
        objJson.add("numColumn", atrNumColumn);
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
