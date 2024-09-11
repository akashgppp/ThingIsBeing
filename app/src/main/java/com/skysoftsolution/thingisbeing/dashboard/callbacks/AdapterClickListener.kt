package com.skysoftsolution.thingisbeing.dashboard.callbacks

import com.skysoftsolution.thingisbeing.dashboard.entity.ModuleForUse

interface AdapterClickListener {
    fun onClickListener(dashBoardModule: ModuleForUse)
}