package com.escodro.alarm.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.escodro.alarm.TaskReceiver
import com.escodro.domain.usecase.task.CompleteTask
import io.reactivex.Single
import org.koin.core.inject
import timber.log.Timber

/**
 * [Worker] to set the Task alarms as completed.
 */
class TaskCompletedWorker(context: Context, params: WorkerParameters) :
    ObservableWorker<Unit>(context, params) {

    private val completeTaskUseCase: CompleteTask by inject()

    override fun getObservable(): Single<Unit> {
        val taskId = inputData.getLong(TaskReceiver.EXTRA_TASK, 0)

        return completeTaskUseCase(taskId)
    }

    override fun onSuccess(result: Unit) {
        Timber.d("Task updated as completed")
    }

    override fun onError(error: Throwable) {
        Timber.d("onError: $error")
    }
}
