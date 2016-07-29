package com.github.xyalan;

import com.github.xyalan.model.v2.App;
import com.github.xyalan.model.v2.DeleteAppTaskResponse;
import com.github.xyalan.model.v2.DeleteAppTasksResponse;
import com.github.xyalan.model.v2.GetAppResponse;
import com.github.xyalan.model.v2.GetAppTasksResponse;
import com.github.xyalan.model.v2.GetAppsResponse;
import com.github.xyalan.model.v2.GetEventSubscriptionRegisterResponse;
import com.github.xyalan.model.v2.GetServerInfoResponse;
import com.github.xyalan.model.v2.GetTasksResponse;
import com.github.xyalan.model.v2.Group;
import com.github.xyalan.model.v2.Result;
import com.github.xyalan.model.v2.Version;
import com.github.xyalan.model.v2.Versions;
import com.github.xyalan.utils.HeaderTemplate;
import com.github.xyalan.utils.MarathonException;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import com.github.xyalan.model.v2.Deployment;
import com.github.xyalan.model.v2.GetEventSubscriptionsResponse;

import java.util.List;

public interface Marathon {
	@RequestLine("GET /v2/apps")
	GetAppsResponse getApps();

	@RequestLine("GET /v2/apps/{id}")
	GetAppResponse getApp(@Param("id") String id) throws MarathonException;

	@RequestLine("GET /v2/apps/{id}/tasks")
	GetAppTasksResponse getAppTasks(@Param("id") String id);

	@RequestLine("GET /v2/tasks")
	GetTasksResponse getTasks();

	@Headers(HeaderTemplate.RequestBody)
	@RequestLine("POST /v2/apps")
	App createApp(App app) throws MarathonException;

	@Headers(HeaderTemplate.RequestBody)
	@RequestLine("PUT /v2/apps/{app_id}")
	void updateApp(@Param("app_id") String appId, App app);

	@Headers(HeaderTemplate.RequestBody)
	@RequestLine("PUT /v2/apps/{app_id}")
	void updateApp(@Param("app_id") String appId, Version version);

	@Headers(HeaderTemplate.RequestBody)
	@RequestLine("POST /v2/apps/{id}/restart?force={force}")
	void restartApp(@Param("id") String id, @Param("force") boolean force);

	@RequestLine("DELETE /v2/apps/{id}")
	Result deleteApp(@Param("id") String id) throws MarathonException;

	@RequestLine("DELETE /v2/apps/{app_id}/tasks?host={host}&scale={scale}")
	DeleteAppTasksResponse deleteAppTasks(@Param("app_id") String appId,
										  @Param("host") String host, @Param("scale") String scale);

	@RequestLine("DELETE /v2/apps/{app_id}/tasks/{task_id}?scale={scale}")
	DeleteAppTaskResponse deleteAppTask(@Param("app_id") String appId,
										@Param("task_id") String taskId, @Param("scale") String scale);

	@Headers(HeaderTemplate.RequestBody)
	@RequestLine("POST /v2/groups")
	Result createGroup(Group group) throws MarathonException;
	
	@RequestLine("DELETE /v2/groups/{id}")
	Result deleteGroup(@Param("id") String id) throws MarathonException;
	
	@RequestLine("GET /v2/groups/{id}")
	Group getGroup(@Param("id") String id) throws MarathonException;

	@RequestLine("GET /v2/deployments")
	List<Deployment> getDeployments();
	
	@RequestLine("DELETE /v2/deployments/{deploymentId}")
	void cancelDeploymentAndRollback(@Param("deploymentId") String id);
	
	@RequestLine("DELETE /v2/deployments/{deploymentId}?force=true")
	void cancelDeployment(@Param("deploymentId") String id);

	@Headers(HeaderTemplate.RequestBody)
    @RequestLine("POST /v2/eventSubscriptions?callbackUrl={url}")
    public GetEventSubscriptionRegisterResponse register(@Param("url") String url);

    @RequestLine("DELETE /v2/eventSubscriptions?callbackUrl={url}")
    public GetEventSubscriptionRegisterResponse unregister(@Param("url") String url);

    @RequestLine("GET /v2/eventSubscriptions")
    public GetEventSubscriptionsResponse subscriptions();

    @RequestLine("GET /v2/info")
	GetServerInfoResponse getServerInfo();

	@RequestLine("GET /v2/apps/{app_id}/versions")
	Versions getAppVersions(@Param("app_id") String appId);
}