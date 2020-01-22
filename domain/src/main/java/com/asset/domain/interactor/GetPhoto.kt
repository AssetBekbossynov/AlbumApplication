package com.asset.domain.interactor

import com.asset.domain.entity.PhotoDomain
import com.asset.domain.repository.AlbumRep
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPhoto (private val repository: AlbumRep, threadExecutor: Scheduler, postExecutionThread: Scheduler)
    :UseCase<List<PhotoDomain>, GetPhoto.Params>(threadExecutor, postExecutionThread){

    override fun buildUseCaseObservable(params: Params?): Observable<List<PhotoDomain>> {
        return repository.getPhoto(params!!.albumId)
    }

    class Params private constructor(val albumId: Int){
        companion object {
            fun getPhoto(albumId: Int): Params{
                return Params(albumId)
            }
        }
    }
}