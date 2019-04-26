package synchronize.consumer;

class MessagesProcesser {
	String name;
	// anonymous inner class that supplies the consumer
	// capabilities for the MessagesProcessor
	private Consumer consumer = new Consumer() {
		// that method is called on each event retrieved
		protected void onConsume(String s) {
			MessagesProcesser.this.processMessage(s);
		}
	}.setName("MessagesProcessor").init();

	public void gotMessageEvent(String s) {
		consumer.add(s);
	}

	private void processMessage(String s) {
		System.out.println(name + " processed message: " + s);
	}

	private void terminate() throws InterruptedException {
		consumer.terminateWait();
		name = null;
	}

	MessagesProcesser() {
		name = "Example Consumer";
	}
}
