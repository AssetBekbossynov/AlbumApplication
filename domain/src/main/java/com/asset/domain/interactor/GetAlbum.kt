package com.asset.domain.interactor

import com.asset.domain.entity.AlbumDomain
import com.asset.domain.repository.AlbumRep
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetAlbum (private val repository: AlbumRep, threadExecutor: Scheduler, postExecutionThread: Scheduler)
    :UseCase<List<AlbumDomain>, GetAlbum.Params>(threadExecutor, postExecutionThread){

    override fun buildUseCaseObservable(params: Params?): Observable<List<AlbumDomain>> {
        return repository.getAlbum(params!!.limit, params.start)
    }

    class Params private constructor(val limit: Int, val start: Int){
        companion object {
            fun getAlbum(limit: Int, start: Int): Params{
                return Params(limit, start)
            }
        }
    }
}
