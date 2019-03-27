var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "30000",
        "ok": "26448",
        "ko": "3552"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "203",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "4779",
        "ok": "4519",
        "ko": "4779"
    },
    "meanResponseTime": {
        "total": "525",
        "ok": "331",
        "ko": "1972"
    },
    "standardDeviation": {
        "total": "716",
        "ok": "368",
        "ko": "975"
    },
    "percentiles1": {
        "total": "219",
        "ok": "215",
        "ko": "1995"
    },
    "percentiles2": {
        "total": "327",
        "ok": "260",
        "ko": "2623"
    },
    "percentiles3": {
        "total": "2381",
        "ok": "968",
        "ko": "3482"
    },
    "percentiles4": {
        "total": "3292",
        "ok": "2125",
        "ko": "4412"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 24653,
        "percentage": 82
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 961,
        "percentage": 3
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 834,
        "percentage": 3
    },
    "group4": {
        "name": "failed",
        "count": 3552,
        "percentage": 12
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "322.581",
        "ok": "284.387",
        "ko": "38.194"
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
        "ok": "26448",
        "ko": "3552"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "203",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "4779",
        "ok": "4519",
        "ko": "4779"
    },
    "meanResponseTime": {
        "total": "525",
        "ok": "331",
        "ko": "1972"
    },
    "standardDeviation": {
        "total": "716",
        "ok": "368",
        "ko": "975"
    },
    "percentiles1": {
        "total": "219",
        "ok": "215",
        "ko": "1995"
    },
    "percentiles2": {
        "total": "327",
        "ok": "260",
        "ko": "2623"
    },
    "percentiles3": {
        "total": "2381",
        "ok": "968",
        "ko": "3482"
    },
    "percentiles4": {
        "total": "3292",
        "ok": "2125",
        "ko": "4412"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 24653,
        "percentage": 82
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 961,
        "percentage": 3
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 834,
        "percentage": 3
    },
    "group4": {
        "name": "failed",
        "count": 3552,
        "percentage": 12
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "322.581",
        "ok": "284.387",
        "ko": "38.194"
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
