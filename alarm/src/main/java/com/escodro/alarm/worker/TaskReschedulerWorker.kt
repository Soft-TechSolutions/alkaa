package com.escodro.alarm.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.escodro.alarm.notification.TaskNotificationScheduler
import com.escodro.domain.usecase.task.GetFutureTasks
import com.escodro.domain.viewdata.ViewData
import io.reactivex.Single
import org.koin.core.inject
import timber.log.Timber

/**
 * [Worker] to reschedule the Task alarms.
 */
class TaskReschedulerWorker(context: Context, params: WorkerParameters) :
    ObservableWorker<MutableList<ViewData.Task>>(context, params) {

    private val getFutureTasksUseCase: GetFutureTasks by inject()

    private val taskAlarmManager: TaskNotificationScheduler by inject()

    override fun getObservable(): Single<MutableList<ViewData.Task>> =
        getFutureTasksUseCase()

    override fun onSuccess(result: MutableList<ViewData.Task>) {
        result.forEach {
            taskAlarmManager.scheduleTaskAlarm(it)
        }
    }

    override fun onError(error: Throwable) {
        Timber.d("onError: $error")
    }
}
