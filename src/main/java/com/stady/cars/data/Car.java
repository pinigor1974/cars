package com.stady.cars.data;

import java.util.List;

public record Car(String name, String sendType, List<String> sendTypes, List<String> codes) {
}
