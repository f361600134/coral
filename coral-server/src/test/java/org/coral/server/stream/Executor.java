package org.coral.server.stream;

@FunctionalInterface
public interface Executor {
	 public <T> T execute() throws Exception;
}
