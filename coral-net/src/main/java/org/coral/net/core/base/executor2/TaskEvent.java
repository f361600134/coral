package org.coral.net.core.base.executor2;

public class TaskEvent {
  private Runnable task;

  public Runnable getTask() {
    return task;
  }

  public void setTask(Runnable task) {
    this.task = task;
  }
}
