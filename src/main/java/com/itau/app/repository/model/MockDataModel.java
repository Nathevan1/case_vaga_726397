package com.itau.app.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MockDataModel {

    @JsonProperty("mock")
    private List<MockDataListModel> mock;
}
