package team.study.composemovieapp.library.storage.usecases

import team.study.composemovieapp.library.storage.IStorage
import javax.inject.Inject

class StorageClearUseCase @Inject constructor(
    private val storage: IStorage
) : IStorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}
