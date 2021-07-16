package ru.itis.androidcoursekfu2.utils

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.resume

suspend fun MotionLayout.awaitTransitionComplete(transitionId: Int, timeout: Long = 5000L) {
    var listener: MotionLayout.TransitionListener? = null

    try {
        withTimeout(timeout) {
            suspendCancellableCoroutine<Unit> { continuation ->
                val l = object : TransitionAdapter() {
                    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                        if (currentId == transitionId) {
                            removeTransitionListener(this)
                            continuation.resume(Unit)
                        }
                    }
                }
                // If the coroutine is cancelled, remove the listener
                continuation.invokeOnCancellation {
                    removeTransitionListener(l)
                }
                // And finally add the listener
                addTransitionListener(l)
                listener = l
            }
        }
    } catch (tex: TimeoutCancellationException) {
        // Transition didn't happen in time. Remove our listener and throw a cancellation
        // exception to let the coroutine know
        listener?.let(::removeTransitionListener)
        throw CancellationException("Transition to state with id: $transitionId did not" +
                " complete in timeout.", tex)
    }
}