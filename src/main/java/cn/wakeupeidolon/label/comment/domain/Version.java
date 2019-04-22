package cn.wakeupeidolon.label.comment.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wang Yu
 */
public class Version implements Serializable {
    private String lasted;
    
    private List<String> description;
    
    public String getLasted() {
        return lasted;
    }
    
    public void setLasted(String lasted) {
        this.lasted = lasted;
    }
    
    public List<String> getDescription() {
        return description;
    }
    
    public void setDescription(List<String> description) {
        this.description = description;
    }
}
