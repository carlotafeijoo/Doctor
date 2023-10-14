package POJOS;

import java.util.Objects;

public class Task {
    //Attributes of Task class
    private int task_id;
    private String description;
    private int duration;
    private Doctor doctor;
    private Elderly elderly_id;
    

    //Empty constructor
    public Task() {
        super();
    }

    //Constructor with all the class Task´s attributes as parameters
    
    //NUEVO CONSTRUCTOR PENTIENTE CON duration -> PARA EVITAR ERRORES Y ENTENDER FUNCIONAMENTO
    public Task (int task_id, String description, Doctor doctor) {
    	super();
    	this.task_id= task_id;
        this.description = description;   
        this.doctor=doctor;
    }
	
    public Task (String description, Doctor doctor) {
    	super();
        this.description = description;   
        this.doctor=doctor;
    }
	
    
	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(Elderly elderly_id) {
		this.elderly_id = elderly_id;
	}
	
	public Elderly getElderly() {
		return elderly_id;
	}

	public void setElderly(Elderly elderly_id) {
		this.elderly_id = elderly_id;
	}

	// Has an equals (uses only description)
    @Override
    public int hashCode() {
        return Objects.hash(task_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
            return Objects.equals(description, other.description);
    }

    @Override
    public String toString() {
        return "\n [Task [description=" + description +", prescribed by Dr. " + doctor.getName() +" ]]";
    } //ver si necesitamos anadir info
    
}

