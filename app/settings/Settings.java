package settings;

public class Settings {

    private String publisherTopicArn;
    private String notificationsQueueUrl;

    public String getPublisherTopicArn() {
        return publisherTopicArn;
    }

    public void setPublisherTopicArn(String publisherTopicArn) {
        this.publisherTopicArn = publisherTopicArn;
    }

    public String getNotificationsQueueUrl() {
        return notificationsQueueUrl;
    }

    public void setNotificationsQueueUrl(String notificationsQueueUrl) {
        this.notificationsQueueUrl = notificationsQueueUrl;
    }
}
