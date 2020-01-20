package com.asset.domain.interactor

import com.fernandocejas.arrow.checks.Preconditions

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<T, Params> internal constructor(private val threadExecutor: Scheduler, private val postExecutionThread: Scheduler) {
    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    internal abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(params: Params, observer: DisposableObserver<T>) {
        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(threadExecutor)
            .observeOn(postExecutionThread)
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Executes the current use case.
     *
     * @param onNext [Consumer] which will accept emissions from the ObservableSource build
     * by [.buildUseCaseObservable] ()} method.
     * @param onError [Consumer] which will accept any error notification from the ObservableSource
     * build by [.buildUseCaseObservable] ()} method.
     * @param onComplete [Consumer] which will accept a completion notification from the ObservableSource
     * build by [.buildUseCaseObservable] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(params: Params,
                onNext: (t: T) -> Unit,
                onError: (exception: Throwable) -> Unit = {},
                onComplete: () -> Unit = {},
                onSubscribe: () -> Unit = {},
                onFinally: () -> Unit = {}) {
        Preconditions.checkNotNull(onNext)
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(threadExecutor)
            .observeOn(postExecutionThread)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
        addDisposable(observable.subscribeWith(object : DefaultObserver<T>() {
            override fun onNext(t: T) {
                onNext(t)
            }

            override fun onError(exception: Throwable) {
                onError(exception)
            }

            override fun onComplete() {
                onComplete()
            }
        }))
    }

    /**
     * Executes the current use case without params.
     *
     * @param onNext [Consumer] which will accept emissions from the ObservableSource build
     * by [.buildUseCaseObservable] ()} method.
     * @param onError [Consumer] which will accept any error notification from the ObservableSource
     * build by [.buildUseCaseObservable] ()} method.
     * @param onComplete [Consumer] which will accept a completion notification from the ObservableSource
     * build by [.buildUseCaseObservable] ()} method.
     */
    fun execute(onNext: (t: T) -> Unit,
                onError: (exception: Throwable) -> Unit = {},
                onComplete: () -> Unit = {},
                onSubscribe: () -> Unit = {},
                onFinally: () -> Unit = {}) {
        Preconditions.checkNotNull(onNext)
        val observable = this.buildUseCaseObservable(null)
            .subscribeOn(threadExecutor)
            .observeOn(postExecutionThread)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
        addDisposable(observable.subscribeWith(object : DefaultObserver<T>() {
            override fun onNext(t: T) {
                onNext(t)
            }

            override fun onError(exception: Throwable) {
                onError(exception)
            }

            override fun onComplete() {
                onComplete()
            }
        }))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}