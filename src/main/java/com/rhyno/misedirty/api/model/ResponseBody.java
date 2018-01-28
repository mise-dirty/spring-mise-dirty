package com.rhyno.misedirty.api.model;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "body")
public class ResponseBody {
    private List<Air> airs;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<Air> getAirs() {
        return airs;
    }

    public void setAirs(List<Air> airs) {
        this.airs = airs;
    }
}
