var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "3000",
        "ok": "3000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "102",
        "ok": "102",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2610",
        "ok": "2610",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "785",
        "ok": "785",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "443",
        "ok": "443",
        "ko": "-"
    },
    "percentiles1": {
        "total": "921",
        "ok": "921",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1157",
        "ok": "1157",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1340",
        "ok": "1340",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1498",
        "ok": "1498",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1330,
        "percentage": 44
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1113,
        "percentage": 37
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 557,
        "percentage": 19
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "30.612",
        "ok": "30.612",
        "ko": "-"
    }
},
contents: {
"req_blocking-14343": {
        type: "REQUEST",
        name: "blocking",
path: "blocking",
pathFormatted: "req_blocking-14343",
stats: {
    "name": "blocking",
    "numberOfRequests": {
        "total": "3000",
        "ok": "3000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "102",
        "ok": "102",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2610",
        "ok": "2610",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "785",
        "ok": "785",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "443",
        "ok": "443",
        "ko": "-"
    },
    "percentiles1": {
        "total": "921",
        "ok": "921",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1157",
        "ok": "1157",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1340",
        "ok": "1340",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1498",
        "ok": "1498",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1330,
        "percentage": 44
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1113,
        "percentage": 37
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 557,
        "percentage": 19
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "30.612",
        "ok": "30.612",
        "ko": "-"
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
