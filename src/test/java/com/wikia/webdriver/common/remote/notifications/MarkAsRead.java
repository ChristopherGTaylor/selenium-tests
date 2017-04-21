package com.wikia.webdriver.common.remote.notifications;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import org.json.JSONObject;

public class MarkAsRead {

  private final static String MARK_AS_READ_SUFFIX = "mark-all-as-read";
  private final PostRemoteOperation remoteOperation;

  MarkAsRead(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public void execute() {
    remoteOperation.execute(buildUrl(), new JSONObject());
  }


  private String buildUrl() {
    return NotificationsOperations.service(MARK_AS_READ_SUFFIX);
  }


}