// INotificationService.aidl
package dora.bugskiller;

interface INotificationService {

    void updateNotification(String title, String content);
    void cancelNotification();
}
