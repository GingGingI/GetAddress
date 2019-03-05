package c.gingdev.getaddress.permissions.values

object permissionsList {
    val permissions = permissions()

    val locationPermissionList: Array<String> = arrayOf(
        permissions.accessFineLocation,
        permissions.accessCorseLocation
    )

    val storagePermissionList: Array<String> = arrayOf(
        permissions.readExternalStorage,
        permissions.writeExternalStorage
    )

    val smsPermissionList: Array<String> = arrayOf(
        permissions.sendSMS,
        permissions.receiveSMS,
        permissions.readSMS,
        permissions.receiveWapPush,
        permissions.receiveMMS
    )

    val sensorPermissionList: Array<String> = arrayOf(permissions.bodySensors)

    val phonePermissionList: Array<String> = arrayOf(
        permissions.readPhoneState,
        permissions.callPhone,
        permissions.readCallLog,
        permissions.writeCallLog,
        permissions.addVoiceMail,
        permissions.useSip,
        permissions.processOutGoingCalls
    )

    val microPhonePsermissionList : Array<String> = arrayOf(permissions.recordAudio)

    val contactsPermissionList: Array<String> = arrayOf(
        permissions.readContacts,
        permissions.writeContacts,
        permissions.getAccounts
    )

    val cameraPermissionList: Array<String> = arrayOf(permissions.camera)

    val calendarPermissionList: Array<String> =arrayOf(
        permissions.readCalendar,
        permissions.writeCalendar
    )
}