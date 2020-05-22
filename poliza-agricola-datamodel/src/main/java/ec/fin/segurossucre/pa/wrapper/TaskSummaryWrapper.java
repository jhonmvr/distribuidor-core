package ec.com.def.pa.wrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummaryWrapper {

	private List<TaskItemWrapper> taskSummary;

	@JsonProperty("task-summary")
	public List<TaskItemWrapper> getTaskSummary() {
		return taskSummary;
	}

	public void setTaskSummary(List<TaskItemWrapper> taskSummary) {
		this.taskSummary = taskSummary;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder("TaskSummaryWrapper:[");
		if(taskSummary != null && !taskSummary.isEmpty()) {
			for (TaskItemWrapper item :taskSummary ) {
				str.append("item:[");			
				str.append(item.getTaskId());
				str.append(",");
				str.append(item.getTaskName());
				str.append(",");
				str.append(item.getTaskProcessInstanceId());
				str.append(",");
				str.append(item.getTaskStatus());
				str.append(",");
				str.append(item.getTaskActualOwner());
				str.append("]");
			}
		}
		str.append("]");
		return "";
		
	}
	
	

}
