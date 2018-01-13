package com.levnovikov.core_base.lifecycle

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

interface Lifecycle {

    fun subscribeUntil(event: LifecycleEvent, disposable: Disposable)

    fun subscribeUntilDestroy(disposable: Disposable)

    fun eventStream(): Observable<LifecycleEvent>
}
