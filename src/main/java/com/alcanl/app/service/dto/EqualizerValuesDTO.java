package com.alcanl.app.service.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class EqualizerValuesDTO implements Serializable {

    @EqualsAndHashCode.Include
    private long equalizerValuesId;

    private List<Integer> values;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String equalizerValuesName;

}
