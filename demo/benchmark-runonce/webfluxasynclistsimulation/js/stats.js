var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "30000",
        "ok": "29925",
        "ko": "75"
    },
    "minResponseTime": {
        "total": "7",
        "ok": "203",
        "ko": "7"
    },
    "maxResponseTime": {
        "total": "979",
        "ok": "979",
        "ko": "877"
    },
    "meanResponseTime": {
        "total": "224",
        "ok": "224",
        "ko": "175"
    },
    "standardDeviation": {
        "total": "76",
        "ok": "76",
        "ko": "215"
    },
    "percentiles1": {
        "total": "208",
        "ok": "208",
        "ko": "83"
    },
    "percentiles2": {
        "total": "212",
        "ok": "212",
        "ko": "275"
    },
    "percentiles3": {
        "total": "244",
        "ok": "243",
        "ko": "795"
    },
    "percentiles4": {
        "total": "664",
        "ok": "662",
        "ko": "873"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 29861,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 64,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 75,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "348.837",
        "ok": "347.965",
        "ko": "0.872"
    }
},
contents: {
"req_async-list-24771": {
        type: "REQUEST",
        name: "async_list",
path: "async_list",
pathFormatted: "req_async-list-24771",
stats: {
    "name": "async_list",
    "numberOfRequests": {
        "total": "30000",
        "ok": "29925",
        "ko": "75"
    },
    "minResponseTime": {
        "total": "7",
        "ok": "203",
        "ko": "7"
    },
    "maxResponseTime": {
        "total": "979",
        "ok": "979",
        "ko": "877"
    },
    "meanResponseTime": {
        "total": "224",
        "ok": "224",
        "ko": "175"
    },
    "standardDeviation": {
        "total": "76",
        "ok": "76",
        "ko": "215"
    },
    "percentiles1": {
        "total": "208",
        "ok": "208",
        "ko": "83"
    },
    "percentiles2": {
        "total": "212",
        "ok": "212",
        "ko": "275"
    },
    "percentiles3": {
        "total": "244",
        "ok": "243",
        "ko": "795"
    },
    "percentiles4": {
        "total": "664",
        "ok": "662",
        "ko": "873"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 29861,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 64,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 75,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "348.837",
        "ok": "347.965",
        "ko": "0.872"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
